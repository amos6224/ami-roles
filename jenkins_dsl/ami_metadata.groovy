job('ami_metadata') {
  description 'Produce metadata for Spinnaker'
  parameters {
    stringParam('BaseAmiName', 'Apache')
    stringParam('Label', 'ApacheRelease')
    stringParam('AmiName', '3.4')
    }
  steps{
    shell('item=\'{\'\
\'"BaseAmiName" : {"S" : "\'"$BaseAmiName"\'"}, \'\
\'"Label" : {"S" : "\'"$Label"\'"}, \'\
\'"AmiName" : {"S" : "\'"$AmiName"\'"}, \'\
\'"Timestamp" : {"S" : "\'"$(date -u +%FT%T%z)"\'"}\'\
\'}\'\
\
aws --region us-west-2 dynamodb put-item --table-name base_ami_versions --item "$item"')
    }
}
