SUMMARY = "Summit Backports for MSD45"

BACKPORTS_CONFIG = "${@bb.utils.contains('DISTRO_FEATURES','bluetooth','wb45n','wb45n_nbt',d)}"

RCONFLICTS:${PN} = " \
        kernel-module-summit-backports-summit \
        kernel-module-sterling-backports-summit \
        kernel-module-lwb-backports-summit \
        kernel-module-lwb5p-backports-summit \
        "

module_conf_ath6kl_core  = "options ath6kl_core recovery_enable=1 heart_beat_poll=200"
module_conf_ath6kl_core += "disable_fw_dbglog=1 suspend_mode=1"

KERNEL_MODULE_PROBECONF += "ath6kl_core"

require summit-backports.inc radio-stack-4550-version.inc
