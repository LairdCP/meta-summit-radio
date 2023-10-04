SUMMARY = "Summit Backports for MSD50"

BACKPORTS_CONFIG = "${@bb.utils.contains('DISTRO_FEATURES','bluetooth','wb50n','wb50n_nbt',d)}"

RCONFLICTS_${PN} = " \
        kernel-module-60-backports-summit \
        kernel-module-lwb-backports-summit \
        kernel-module-msd45-backports-summit \
        kernel-module-nx-backports-summit \
        kernel-module-qcacld-backports-summit \
        "

module_conf_ath6kl_core  = "options ath6kl_core recovery_enable=1 heart_beat_poll=200"
module_conf_ath6kl_core += "btcoex_chip_type=2 btcoex_ant_config=4"
module_conf_ath6kl_core += "disable_fw_dbglog=1 suspend_mode=1"

KERNEL_MODULE_PROBECONF += "ath6kl_core"

require summit-backports.inc radio-stack-4550-version.inc
