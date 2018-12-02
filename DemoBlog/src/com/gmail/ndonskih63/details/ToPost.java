package com.gmail.ndonskih63.details;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToPost {

        private final Map<String, ThisPost> thisPostMap = new HashMap<>();

        public void addPost(ThisPost tp) {

            thisPostMap.put(tp.getId(), tp);
        }

        public void deleteThisPost(String id) {

            thisPostMap.remove(id);
        }

        public List<ThisPost> getPost() {

            return new ArrayList<>(thisPostMap.values());
        }
    }

