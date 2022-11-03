[ -z "${1}" ] && { echo "Usage: $0 <version>"; exit 1; }

file="radio-stack-lwb-hashes.inc"
prefix="https://files.devops.rfpros.com/builds/linux"
ver=${1}

calc_file () {
  name=${1##*/}
  wget ${1} || exit 1
  echo "SRC_URI[${2}.md5sum] = \"$(md5sum ${name} | awk '{print $1}')\"" >> ${file}
  echo "SRC_URI[${2}.sha256sum] = \"$(sha256sum ${name} | awk '{print $1}')\"" >> ${file}
  rm -f ${name}
}

echo -e "PV = \"${ver}\"\n" > ${file}

calc_file "${prefix}/sterling_supplicant/src/${ver}/sterling_supplicant-src-${ver}.tar.gz" "sterling-supplicant"
calc_file "${prefix}/backports/laird/${ver}/backports-laird-${ver}.tar.bz2" "summit-backports"
calc_file "${prefix}/firmware/${ver}/laird-lwbplus-firmware-${ver}.tar.bz2" "lwbplus-firmware"

for i in etsi fcc jp
do
  calc_file "${prefix}/firmware/${ver}/laird-lwb-${i}-firmware-${ver}.tar.bz2" "lwb-${i}-firmware"
done

for i in etsi fcc ic jp
do
  calc_file "${prefix}/firmware/${ver}/laird-lwb5-${i}-firmware-${ver}.tar.bz2" "lwb5-${i}-firmware"
done

for i in sdio-div sdio-sa sdio-sa-m2 usb-div usb-sa usb-sa-m2
do
  calc_file "${prefix}/firmware/${ver}/laird-lwb5plus-${i}-firmware-${ver}.tar.bz2" "lwb5plus-${i}-firmware"
done
