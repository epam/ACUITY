#
# Copyright 2021 The University of Manchester
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

docker run -it --rm --name my-running-script \
  -v "$PWD":/usr/src/myapp \
  -v "$HOME/azure-credentials":/usr/src/myapp/azure-credentials \
  -v "$HOME/acuity/clns-acuity-docker":/usr/src/myapp/acuity-docker \
  -w /usr/src/myapp python:3.6-slim  /bin/bash -c "pip install pyyaml;chmod +x apply-azure-configurations.py;./apply-azure-configurations.py"
docker rmi python:3.6-slim
