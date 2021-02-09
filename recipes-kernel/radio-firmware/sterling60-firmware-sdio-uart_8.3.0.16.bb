SUMMARY = "Laird Connectivity Sterling 60 Firmware SDIO-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "704d1bcfeed584983ccd64b1d038e35c"
SRC_URI[sha256sum] = "99a1ebdde9cd21fdf2942fb07903116b51d9795709840f24fd4ddae15e76b7ba"

include radio-firmware.inc
