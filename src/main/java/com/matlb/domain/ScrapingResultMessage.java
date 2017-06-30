package com.matlb.domain;

/**
 * Created by prassingh on 6/19/16.
 */
public class ScrapingResultMessage {
    private String url;
    private String summary;
    private String codeSnippets;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getCodeSnippets()
    {
        return codeSnippets;
    }

    public void setCodeSnippets(String codeSnippets)
    {
        this.codeSnippets = codeSnippets;
    }
}
