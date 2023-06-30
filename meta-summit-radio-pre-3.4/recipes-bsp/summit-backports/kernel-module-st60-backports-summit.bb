SUMMARY = "Summit Backports for ST60"

BACKPORTS_CONFIG = "${@bb.utils.contains('DISTRO_FEATURES','bluetooth','sterling60','sterling60_nbt',d)}"

RCONFLICTS_${PN} = " \
        kernel-module-summit-backports-summit \
        kernel-module-lwb-backports-summit \
        kernel-module-lwb5p-backports-summit \
        "

require summit-backports.inc radio-stack-st60-version.inc
