SUMMARY = "Laird Sterling 60 Firmware PCIE-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "c29b8e85d374fae5f57825455b7436fb"
SRC_URI[sha256sum] = "1507f57ba31294ce5e625305bb5ab2320a621aef51e00bb86835e069e30f73c2"

include radio-firmware.inc
