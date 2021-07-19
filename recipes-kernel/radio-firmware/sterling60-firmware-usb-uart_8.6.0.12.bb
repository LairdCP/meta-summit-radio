SUMMARY = "Laird Connectivity Sterling 60 Firmware USB-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "4307c014d07f792ea940d9c0a691d04b"
SRC_URI[sha256sum] = "4cfb37167aa5123a442501e7026d83f4ba0944d22abd6b23069c28a8009785cf"

include radio-firmware-60.inc
