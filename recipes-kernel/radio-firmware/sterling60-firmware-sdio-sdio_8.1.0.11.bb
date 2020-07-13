SUMMARY = "Laird Connectivity Sterling 60 Firmware SDIO-SDIO"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "3a36f35d966d10c758a3e6be3f674e58"
SRC_URI[sha256sum] = "aa930152f76e284adb50e476bef672e8e7492711bb86d197195b008962c2f577"

include radio-firmware.inc
