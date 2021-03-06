package fr.ippon.tatami.web.rest;

import fr.ippon.tatami.domain.Tweet;
import fr.ippon.tatami.service.TimelineService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

/**
 * REST controller for managing tweets.
 *
 * @author Julien Dubois
 */
@Controller
public class TweetController {

    private final Log log = LogFactory.getLog(TweetController.class);

    @Inject
    private TimelineService timelineService;

    @RequestMapping(value = "/rest/tweets/{login}/{nbTweets}",
    		method = RequestMethod.GET,
    		produces = "application/json")
    @ResponseBody
    public Collection<Tweet> listTweets(@PathVariable("login") String login, @PathVariable("nbTweets") String nbTweets) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get the tweet list (" + nbTweets + " sized).");
        }
        try {
			return timelineService.getTimeline(login, Integer.parseInt(nbTweets));
		} catch (NumberFormatException e) {
			log.warn("Page size undefined ; sizing to default", e);
			return timelineService.getTimeline(login, 20);
		}
    }

    @RequestMapping(value = "/rest/ownTweets/{login}",
    		method = RequestMethod.GET,
    		produces = "application/json")
    @ResponseBody
    public Collection<Tweet> listTweets(@PathVariable("login") String login) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get the own tweet list (" + login + ").");
        }
		return timelineService.getUserline(login, 20);
    }

    @RequestMapping(value = "/rest/tweets",
            method = RequestMethod.POST)
    public void postTweet(@RequestBody String content) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to add tweet : " + content);
        }
        timelineService.postTweet(content);
    }
}
