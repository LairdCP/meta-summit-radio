SUMMARY = "Laird Connectivity Sterling 60 Firmware USB-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "41cb90a0b52bd60f0d3297c4c0fcb8cc"
SRC_URI[sha256sum] = "da4dc7fcd1172abcc0eea09e37095419af5fdc28ea5a0d3e139c6a72f4f8793b"

include radio-firmware.inc
