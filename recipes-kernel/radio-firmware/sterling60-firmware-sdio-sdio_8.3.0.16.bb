SUMMARY = "Laird Connectivity Sterling 60 Firmware SDIO-SDIO"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "a116febf570ccd4d2891e11b49814ed3"
SRC_URI[sha256sum] = "b628542a8c57515eee3239776dec30d5a1615234800b1b9ede0558ee4ddaaa2c"

include radio-firmware.inc
