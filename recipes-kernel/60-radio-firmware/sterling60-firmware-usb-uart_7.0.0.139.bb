SUMMARY = "Laird Sterling 60 Firmware USB-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "995c92a9e19ffd5f1fe4f7fbb359f96e"
SRC_URI[sha256sum] = "12ea67e7bfa629a3163d55c7264abcca78f29686339567bf691494551f4c5942"

include radio-firmware.inc
