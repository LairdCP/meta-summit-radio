SUMMARY = "Summit Backports for LWB5+"

BACKPORTS_CONFIG = "${@bb.utils.contains('DISTRO_FEATURES','bluetooth','lwb5p','lwb5p_nbt',d)}"

RCONFLICTS_${PN} = " \
        kernel-module-60-backports-summit \
        kernel-module-lwb-backports-summit \
        "

require summit-backports.inc radio-stack-lwb-version.inc
