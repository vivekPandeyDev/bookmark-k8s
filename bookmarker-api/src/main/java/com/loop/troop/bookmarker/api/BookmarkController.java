package com.loop.troop.bookmarker.api;

import com.loop.troop.bookmarker.domain.BookmarksDTO;
import com.loop.troop.bookmarker.domain.CreateBookmarkRequest;
import com.loop.troop.bookmarker.domain.BookmarkDTO;
import com.loop.troop.bookmarker.domain.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping
    public BookmarksDTO getBookmarks(@RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "") String query) {
        if(query == null || query.trim().isEmpty()) {
            return bookmarkService.getBookmarks(page);
        }
        return bookmarkService.searchBookmarks(query, page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookmarkDTO createBookmark(@RequestBody @Valid CreateBookmarkRequest request) {
        return bookmarkService.createBookmark(request);
    }
}
