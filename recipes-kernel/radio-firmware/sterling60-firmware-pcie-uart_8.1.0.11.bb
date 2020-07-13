SUMMARY = "Laird Connectivity Sterling 60 Firmware PCIE-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "6814fb1d450fbe848aa8d907a411a8f4"
SRC_URI[sha256sum] = "dc013c88f68685f25fc26e6124feb7dad21bc4a2053a3138b615fa4798bd74a4"

include radio-firmware.inc
