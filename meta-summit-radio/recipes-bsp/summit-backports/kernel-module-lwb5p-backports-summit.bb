SUMMARY = "Summit Backports for LWB5+"

BACKPORTS_CONFIG = " \
	${@bb.utils.contains('DISTRO_FEATURES','bluetooth','defconfig-lwb5p','defconfig-lwb5p_nbt',d)} \
"

RCONFLICTS:${PN} = " \
        kernel-module-su60-backports-summit \
        kernel-module-st60-backports-summit \
        kernel-module-lwb-backports-summit \
        "

require summit-backports.inc radio-stack-lwb-version.inc
