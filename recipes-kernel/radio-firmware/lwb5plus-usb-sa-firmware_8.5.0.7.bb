SUMMARY = "Laird Connectivity firmware for the LWB5+ USB/USB module with a single antenna"

SRC_URI += "${LRD_LWB_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "f8420f3d85340496d71f04890c2ca518"
SRC_URI[sha256sum] = "399f3976d94c89a5c2bfb807d2c711968bb51b65b9a7791bcd099bc93e329289"

include radio-firmware-lwb.inc
