#Import the necessary methods from tweepy library
from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream

#Variables that contains the user credentials to access Twitter API 
access_token = "3799078532-mVMadWJsT36InVc5okZ4UsQa4klMqtIsYYcaUmV"
access_token_secret = "mexBIi4bk7JgWH9Ecw2233q85Q1ca2t2yJA0uDb3zypnS"
consumer_key = "1H7kUBzkH60GXamD5eVAys1cK"
consumer_secret = "yxXSDJsHBiwZlHHIP0xu8MW6QtT398iuPhVR2z3La65tav1Uiw"


#This is a basic listener that just prints received tweets to stdout.
class StdOutListener(StreamListener):

    def on_data(self, data):
        print data
        return True

    def on_error(self, status):
        print status


if __name__ == '__main__':

    #This handles Twitter authetification and the connection to Twitter Streaming API
    l = StdOutListener()
    auth = OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_token, access_token_secret)
    stream = Stream(auth, l)

    #This line filter Twitter Streams to capture data by the keywords: 'python', 'javascript', 'ruby'
    stream.filter(track=['festival', 
    	'travel', 
    	'festive',
    	'vacation',
    	'holiday',
    	'blogger',
    	'destination',
    	'leisure',
    	'sightseeing',
    	'tourism',
    	'tour',
    	'travellife',
    	'placetobe',
    	'photography',
    	'oneheart',
    	'traveling',
    	'TTOT',
    	'wanderlust',
    	'RTW',
    	'cruise',
    	'adventure',
    	'beautiful',
    	'nature',
    	'hotel',
    	'landscape',
    	'foodporn',
    	'luxury',
    	'letsgoeverywhere',
    	'festival',
    	'wanderer',
    	'TravelTuesday',
    	'TNI',
    	'festival',
    	'travelblogger',
    	'photooftheday',
    	'trip'
    	])
    #stream.firehose()
