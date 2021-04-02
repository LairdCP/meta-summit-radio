SUMMARY = "Laird Connectivity Sterling 60 Firmware SDIO-SDIO"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "d9bd99592b936760ff29949885788801"
SRC_URI[sha256sum] = "7f746ed704bbd5e86ae8da3cdd9559cbb70d1020471e7ee9c84abec931dc321b"

include radio-firmware-60.inc
