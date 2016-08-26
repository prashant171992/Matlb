package com.matlb.rabbitMq;

import com.matlb.dao.BookmarkDao;
import com.matlb.domain.Bookmark;
import com.matlb.domain.ScrapingResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by prassingh on 6/27/16.
 */
@Component
public class ScrapingResultHandler {
    @Autowired
    private BookmarkDao bookmarkDao;

    public void handleMessage(ScrapingResultMessage scrapingResultMessage)
    {
        System.out.println("Received summary: " + scrapingResultMessage.getSummary());
        final String url = scrapingResultMessage.getUrl();
        final List<Bookmark> bookmarks = bookmarkDao.findByUrl(url);
        if (bookmarks.size() == 0)
        {
            System.out.println("No bookmark of url: " + url + " found.");
            Bookmark bookmark = new Bookmark();
            bookmark.setUrl(url);
            bookmark.setSummary(scrapingResultMessage.getSummary());
            bookmarkDao.save(bookmark);
        }
        else
        {
            for (Bookmark bookmark : bookmarks)
            {
                bookmark.setSummary(scrapingResultMessage.getSummary());
                bookmarkDao.save(bookmark);
                System.out.println("updated bookmark: " + url);
            }
        }
    }
}
