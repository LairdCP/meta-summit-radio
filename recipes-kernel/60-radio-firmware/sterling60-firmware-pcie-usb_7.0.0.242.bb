SUMMARY = "Laird Sterling 60 Firmware PCIE-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "0a0af0b9aae182532909fbfae7f00645"
SRC_URI[sha256sum] = "dae8d6d2bec20443d3b45a0b1716564d2efc64a75b6eea11a9c2e583c96b2e79"

include radio-firmware.inc
