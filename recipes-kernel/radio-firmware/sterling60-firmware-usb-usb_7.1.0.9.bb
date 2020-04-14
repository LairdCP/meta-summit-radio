SUMMARY = "Laird Connectivity Sterling 60 Firmware USB-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "60c3bebdd052438f464d2b1db3ae25cd"
SRC_URI[sha256sum] = "b7349938ef478d1b8e247c6be57e780c6e78d31e8754a11c802c4583bb1a59c5"

include radio-firmware.inc
