SUMMARY = "Summit Backports for LWB5+"

BACKPORTS_CONFIG = " \
	${@bb.utils.contains('DISTRO_FEATURES','bluetooth','defconfig-lwb5p','defconfig-lwb5p_nbt',d)} \
"

RCONFLICTS_${PN} = " \
        kernel-module-summit-backports-summit \
        kernel-module-sterling-backports-summit \
        kernel-module-lwb-backports-summit \
        "

require summit-backports.inc radio-stack-lwb-version.inc
