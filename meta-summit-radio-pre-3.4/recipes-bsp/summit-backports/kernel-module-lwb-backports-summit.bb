SUMMARY = "Summit Backports for LWB and LWB5"

BACKPORTS_CONFIG = "${@bb.utils.contains('DISTRO_FEATURES','bluetooth','lwb','lwb_nbt',d)}"

RCONFLICTS_${PN} = " \
        kernel-module-60-backports-summit \
        kernel-module-lwb5p-backports-summit \
        "

require summit-backports.inc radio-stack-lwb-version.inc
