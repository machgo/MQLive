#!/usr/local/bin/python2
import sys
import logging
from optparse import OptionParser
import os
import gpxpy
import gpxpy.gpx
import time
from subprocess import check_output

def process_file(path, options):
    logging.info("processing " + path)
    gpx_file = open(path, 'r')

    gpx = gpxpy.parse(gpx_file)

    for track in gpx.tracks:
        for segment in track.segments:
            for point in segment.points:
                set_point(point, options)
                time.sleep(options.interval)

    for route in gpx.routes:
        for point in route:
            set_point(point, options)
            time.sleep(options.interval)


def set_point(point, options):
    logging.info('Point at ({0},{1},{2})'.format(point.latitude, point.longitude, point.elevation))
    logging.debug(check_output([options.command, "-c", "gps setlatitude " + str(point.latitude)]))
    logging.debug(check_output([options.command, "-c", "gps setlongitude " + str(point.longitude)]))
    logging.debug(check_output([options.command, "-c", "gps setaltitude " + str(point.elevation)]))


if __name__=='__main__':
    usage = "usage: %prog "
    parser = OptionParser(usage=usage,
        description="Read a gpx file and use it to send points to Genymotion emulator at a fixed interval")
    parser.add_option("-d", "--debug", action="store_true", dest="debug")
    parser.add_option("-q", "--quiet", action="store_true", dest="quiet")
    parser.add_option("-i", "--interval", type="int", dest="interval", default="2",
        help="interval between points in seconds, defaults to 2 seconds")
    parser.add_option("-g", "--genymotion-shell", dest="command",
        help="path to the genyshell command, defaults to /Applications/Genymotion Shell.app/Contents/MacOS/genyshell",
        default="/Applications/Genymotion Shell.app/Contents/MacOS/genyshell")
    (options, args) = parser.parse_args()
    
    logging.basicConfig(level=logging.DEBUG if options.debug else 
        (logging.ERROR if options.quiet else logging.INFO))
 
    logging.debug("using genymotion shell at " + options.command)

    for path in args:
        if not os.path.exists(path):
            logging.error(path + " not found")
            continue
        process_file(path, options)



