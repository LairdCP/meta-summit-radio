SUMMARY = "Laird Connectivity Sterling 60 Firmware USB-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "be9a70b8781b61227b849bf9089c6573"
SRC_URI[sha256sum] = "6ce8dc2d21ff73e6feb9fdc679eb3597fd264f79e28b842562ba32c8679149be"

include radio-firmware.inc
