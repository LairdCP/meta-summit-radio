SUMMARY = "Laird Sterling 60 Firmware SDIO-SDIO"

SRC_URI += "${LRD_60_URI_BASE}/laird-${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "68a582ea9ab5a25038c2b66183704c89"
SRC_URI[sha256sum] = "b42a99bd6ed1c372f3e50ae0b75c49e4de4570eac768fc686abaaf5a1c1b2cf5"

include radio-firmware.inc
