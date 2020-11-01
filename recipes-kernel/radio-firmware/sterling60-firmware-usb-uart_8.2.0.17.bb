SUMMARY = "Laird Connectivity Sterling 60 Firmware USB-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "8e14fd6bb9bccd892ec1a43908b60ba1"
SRC_URI[sha256sum] = "129e53fad8fb50d15bdba67b995e1d188a9cd89881740e294f29ad89784d1601"

include radio-firmware.inc
