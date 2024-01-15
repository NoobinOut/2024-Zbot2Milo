from threading import Thread
import cv2
import numpy
import apriltag

class myWebcamVideoStream:
  def __init__(self, src=0):
    
    # initialize the video camera stream and read the 
    # first frame from the stream
    self.stream = cv2.VideoCapture(src) 
    (self.grabbed, self.frame) = self.stream.read()

    # flag to stop the thread

    self.stopped = False

  def start(self):
    # start the thread to read frames
    Thread(target=self.update, args=()).start()
    return self

  def update(self):

    while True:
       # have we been told to stop?  If so, get out of here
       if self.stopped:
           return

       # otherwise, get another frame
       (self.grabbed, self.frame) = self.stream.read()

  def read(self):
      # return the most recent frame
      return self.frame

  def stop(self):
      # signal thread to end
      self.stopped = True
      return

def plotPoint(image, center, color):
    center = (int(center[0]), int(center[1]))
    image = cv2.line(image,
                     (center[0] - 5, center[1]),
                     (center[0] + 5, center[1]),
                     color,
                     3)
    image = cv2.line(image,
                     (center[0], center[1] - 5),
                     (center[0], center[1] + 5),
                     color,
                     3)
    return image


# main program
vs = myWebcamVideoStream(0).start() 
options = apriltag.DetectorOptions(families="tag36h11")
detector = apriltag.Detector(options)

if not vs:
   print("no image")

iteration = 0
saved = False

while iteration < 500:
   frame = vs.read()
   grayimage = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
   cv2.imshow('frame', frame)
   cv2.imwrite("fulmer2.jpg",frame)

   detections = detector.detect(grayimage)
   if not detections:
       #print("Nothing")
       cv2.putText(frame, "Nothing Detected", (500,500), cv2.FONT_HERSHEY_SIMPLEX, 2.0, (0, 0, 255), 2)
       cv2.imshow('frame', frame)
       #cv2.imwrite("fulmer.jpg",frame)
       cv2.waitKey(1)
       continue
   else:
       for detect in detections:
           print("tag_id: %s, center: %s, corners: %s, corner.top_left: %s , corner.bottom-right: %s" % (detect.tag_id, detect.center, detect.corners[0:], detect.corners[0], detect.corners[2]))
           frame=plotPoint(frame, detect.center, (255,0,255)) #purpe center
           cornerIndex=0
           for corner in detect.corners:
               if cornerIndex== 0:
                print("top left corner %s" %(corner[0]))
                frame=plotPoint(frame, corner, (0,0,255)) #red for top left corner
                #xord=int(corner[0])
                #yord=int(corner[1])
                org=(int(corner[0]),int(corner[1]))
                tagId=("AprilTagId %s" % (str(detect.tag_id)))
                cv2.putText(frame, tagId, org, cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 255, 0), 2)
               elif cornerIndex == 2:
                print("bottom right corner %s" %(corner[0]))
                frame=plotPoint(frame, corner, (0,255,0)) #green for bottom right corner
               else:
                frame=plotPoint(frame, corner, (0,255,255)) #yellow corner
               cornerIndex+=1
       if not saved:
           cv2.imwrite("fulmer.jpg",frame)
           saved = True
           print("Saved!")
   cv2.imshow('frame', frame)
   cv2.waitKey(1)
   iteration = iteration + 1

vs.stop()
cv2.destroyAllWindows()