SUMMARY = "Laird Connectivity firmware for the LWB5+ USB/USB module with diversity antenna"

SRC_URI += "${LRD_LWB_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "d1125dc621883a24c917ff3eafeb4098"
SRC_URI[sha256sum] = "ec467c83a306c20f2c8a39283fd7e030a93968f6ae55abfaeee9931ae36d7247"

include radio-firmware-lwb.inc
