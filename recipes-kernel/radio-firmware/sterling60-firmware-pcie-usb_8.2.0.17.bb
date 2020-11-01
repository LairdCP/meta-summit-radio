SUMMARY = "Laird Connectivity Sterling 60 Firmware PCIE-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "78cb82f67bf70d192da7f6dfb46953f3"
SRC_URI[sha256sum] = "d305958cb1419275dd21964b53644732591a22b8284ae9b0f408ad7f7b268e55"

include radio-firmware.inc
