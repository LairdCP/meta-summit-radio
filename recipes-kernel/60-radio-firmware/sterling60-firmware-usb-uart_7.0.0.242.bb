SUMMARY = "Laird Sterling 60 Firmware USB-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "b4c88406780fe4b826e3cf9890ec688f"
SRC_URI[sha256sum] = "b3da10392979bad177c5e945631c5017eef778f756a500d5173da8f7bd9aff6f"

include radio-firmware.inc
