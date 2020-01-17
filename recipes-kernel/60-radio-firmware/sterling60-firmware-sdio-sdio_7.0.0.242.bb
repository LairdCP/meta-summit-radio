SUMMARY = "Laird Sterling 60 Firmware SDIO-SDIO"

SRC_URI += "${LRD_60_URI_BASE}/laird-${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "ecf31565a5b4667c1c7a45273cbe2c80"
SRC_URI[sha256sum] = "e01d332a04b3fb56b1896b2e37bec7ab8129490a771cb009fcabd94f72c8c205"

include radio-firmware.inc
