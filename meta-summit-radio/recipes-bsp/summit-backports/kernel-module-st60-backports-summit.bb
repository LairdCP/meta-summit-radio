SUMMARY = "Summit Backports for ST60"

BACKPORTS_CONFIG = " \
	${@bb.utils.contains('DISTRO_FEATURES','bluetooth','defconfig-sterling60','defconfig-sterling60_nbt',d)} \
"

RCONFLICTS:${PN} = " \
        kernel-module-su60-backports-summit \
        kernel-module-lwb-backports-summit \
        kernel-module-lwb5p-backports-summit \
        "

require summit-backports.inc radio-stack-st60-version.inc
