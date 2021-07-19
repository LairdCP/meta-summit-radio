SUMMARY = "Laird Connectivity Sterling 60 Firmware SDIO-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "3a41fddfe9804e8cfff7a053550ec451"
SRC_URI[sha256sum] = "2781528902f7fd08f34852786b87f60f2fcc4d412188a894ab68fdd69a455d37"

include radio-firmware-60.inc
