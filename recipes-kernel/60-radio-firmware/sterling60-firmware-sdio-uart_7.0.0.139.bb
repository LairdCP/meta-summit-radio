SUMMARY = "Laird Sterling 60 Firmware SDIO-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "2fef543ba0e106f74a80f2795fd9c256"
SRC_URI[sha256sum] = "f02862d409de8a7ddf48bd55d2e20a038bcf07b372191cbdeaa3267d2fa50052"

include radio-firmware.inc
