SUMMARY = "Laird Connectivity Sterling 60 Firmware SDIO-SDIO"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "33f4cb5ad125bf7eb6687fd80dbe5609"
SRC_URI[sha256sum] = "0f7222c49afa6ddce9c98f11b44a9e49a316f3c7b2f47118bfd58da84e43644e"

include radio-firmware.inc
