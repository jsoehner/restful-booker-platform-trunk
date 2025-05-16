#!/bin/bash

for dir in */; do
  if [ -f "$dir/Dockerfile" ]; then
    image_name=$(basename "$dir")
    echo "Building image: $image_name"
    docker build --build-arg "BUILDKIT_DOCKERFILE_CHECK=skip=JSONArgsRecommended" -t "$image_name" "$dir"
  else
    echo "No Dockerfile found in $dir"
  fi
done
