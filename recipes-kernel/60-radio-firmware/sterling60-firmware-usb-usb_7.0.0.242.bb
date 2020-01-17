SUMMARY = "Laird Sterling 60 Firmware USB-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "e07a004377beca6b4c7e4125932006a2"
SRC_URI[sha256sum] = "a8205c05c31ba18f2c469a24abed7c62c3060de96e842786281fe1699fa932b0"

include radio-firmware.inc
