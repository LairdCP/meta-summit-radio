SUMMARY = "Laird Connectivity firmware for the LWB SDIO/UART module for operation in the European Union"

SRC_URI += "${LRD_LWB_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "7654dabc934e535a97eda31d5475a8b1"
SRC_URI[sha256sum] = "12ccc8931ce0ed90cab897ab6d165a97db021271d99fe03b5495583eb1ec4d94"

include radio-firmware-lwb.inc
