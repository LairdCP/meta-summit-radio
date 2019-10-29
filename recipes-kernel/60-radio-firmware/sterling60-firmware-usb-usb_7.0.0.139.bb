SUMMARY = "Laird Sterling 60 Firmware USB-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "5458c6a9c61e139b23ce997a81d6489b"
SRC_URI[sha256sum] = "789d99e775adb03ee383f6927c53f8d18665622c54190dbef03fa1991900b72c"

include radio-firmware.inc
