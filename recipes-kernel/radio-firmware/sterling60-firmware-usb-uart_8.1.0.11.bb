SUMMARY = "Laird Connectivity Sterling 60 Firmware USB-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "3218cba386a8adfc7f126da501e43c97"
SRC_URI[sha256sum] = "eab9fae79a2ab6354cab028063f679c4905c0614b1c77483d8dc667b345fe60e"

include radio-firmware.inc
