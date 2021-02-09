SUMMARY = "Laird Connectivity Sterling 60 Firmware USB-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "4fbc4af686bea1cd249c505eddff8a20"
SRC_URI[sha256sum] = "46274326ddba9636a324324ac3e209b43e70a683939b436f7858ec8ec6e5d83b"

include radio-firmware.inc
