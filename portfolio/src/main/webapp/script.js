// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds comments from /data to the page.
 */
function getComments() {
  fetch("/data")
    .then((response) => response.json())
    .then((comments) => {
      console.log(comments);
      document.getElementById("notes-container").innerHTML = `${comments
        .map(
          (comment) =>
            ```
            <p>${comment.comment}</p>
            ${
              comment.imgURL &&
              `<img src="${comment.imgURL}" alt="Image for ${comment.comment}">`
            }
          ```
        )
        .join("")}`;
    });
}

// Fetches Blobstore URL for form action to faciliate upload of images
function getBlobstoreURL() {
  fetch("/blobstore-upload-url")
    .then((response) => {
      return response.text();
    })
    .then((imageUploadUrl) => {
      const commentForm = document.getElementById("comment-form");
      commentForm.action = imageUploadUrl;
    });
}

getBlobstoreURL();
getComments();
