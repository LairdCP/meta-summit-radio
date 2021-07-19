SUMMARY = "Laird Connectivity Sterling 60 Firmware SDIO-SDIO"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "8436e08112f5cee0cee0087514de99ce"
SRC_URI[sha256sum] = "ac3a69ae140689fe42e0d7b0938f9309f91eff0bafc9da735e55a7e3a933e00c"

include radio-firmware-60.inc
