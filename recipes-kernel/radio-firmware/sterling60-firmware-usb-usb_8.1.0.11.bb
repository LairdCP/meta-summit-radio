SUMMARY = "Laird Connectivity Sterling 60 Firmware USB-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "30c47eba2ff41a2fc334add7dddffb9d"
SRC_URI[sha256sum] = "06133daf0ce2e18cfeef772c045b8f3952abaa3b8f722bd57d205a8076b7ddff"

include radio-firmware.inc
