package com.uimirror.poc.rest.util;

/**
 * Created by jpradhan on 7/20/15.
 */
public class ErrorCodes {
    //Prevent creating new instance
    private ErrorCodes(){
        //NOP
    }
    //1xx series
    /**
     * Continue
     * This means that the server has received the request headers, and that the client should proceed to send the request body
     * (in the case of a request for which a body needs to be sent; for example, a POST request)
     */
    public static final int _100 = 100;

    /**
     * Switching Protocols
     * This means the requester has asked the server to switch protocols and the server is acknowledging that it will do so.
     */
    public static final int _101 = 101;

    /**
     * Processing (WebDAV; RFC 2518)
     * As a WebDAV request may contain many sub-requests involving file operations, it may take a long time to complete the request. This code indicates that the server
     *  has received and is processing the request, but no response is available yet.[2] This prevents the client from timing out and assuming the request was lost.
     */
    public static final int _102 = 102;

    //2xx series

    /**
     * OK
     * Standard response for successful HTTP requests.
     */
    public static final int _200 = 200;

    /**
     * Created
     * The request has been fulfilled and resulted in a new resource being created.
     */
    public static final int _201 = 201;

    /**
     * Accepted
     * The request has been accepted for processing, but the processing has not been completed. The request might or might not eventually be acted upon,
     * as it might be disallowed when processing actually takes place.
     */
    public static final int _202 = 202;

    /**
     * Non-Authoritative Information (since HTTP/1.1)
     * The server successfully processed the request, but is returning information that may be from another source.
     */
    public static final int _203 = 203;

    /**
     * No Content
     */
    public static final int _204 = 204;

    /**
     * Reset Content
     */
    public static final int _205 = 205;

    /**
     * Partial Content
     * The server is delivering only part of the resource (byte serving) due to a range header sent by the client.
     */
    public static final int _206 = 206;

    /**
     * Multi-Status (WebDAV; RFC 4918)
     * The message body that follows is an XML message and can contain a number of separate response codes, depending on how many sub-requests were made.
     */
    public static final int _207 = 207;

    /**
     * Already Reported (WebDAV; RFC 5842)
     * The members of a DAV binding have already been enumerated in a previous reply to this request, and are not being included again.
     */
    public static final int _208 = 208;

    /**
     * IM Used (RFC 3229) The server has fulfilled a GET request for the resource, and the response is a representation of the result of one or more
     * instance-manipulations applied to the current instance.
     */
    public static final int _226 = 226;

    //3xx series

    /**
     * Multiple Choices
     * Indicates multiple options for the resource that the client may follow. It, for instance, could be used to present different format options for video, list files with different
     * extensions,	or word sense disambiguation.
     */
    public static final int _300 = 300;

    /**
     *  Moved Permanently
     */
    public static final int _301 = 301;

    /**
     * Found
     */
    public static final int _302 = 302;

    /**
     *  See Other (since HTTP/1.1)
     * The response to the request can be found under another URI using a GET method. When received in response to a POST (or PUT/DELETE), it should be assumed that the server has
     * received the data and the redirect should be issued with a separate GET message.
     */
    public static final int _303 = 303;

    /**
     * Not Modified
     * Indicates that the resource has not been modified since the version specified by the request headers If-Modified-Since or If-Match. This means that there is no need to
     *  retransmit the resource, since the client still has a previously-downloaded copy.
     */
    public static final int _304 = 304;

    /**
     * Use Proxy (since HTTP/1.1)
     * The requested resource is only available through a proxy, whose address is provided in the response.
     */
    public static final int _305 = 305;

    /**
     * Switch Proxy
     * No longer used. Originally meant "Subsequent requests should use the specified proxy."
     */
    public static final int _306 = 306;

    /**
     * Temporary Redirect (since HTTP/1.1)
     * In this case, the request should be repeated with another URI; however, future requests should still use the original URI.
     * In contrast to how 302 was historically implemented, the request method is not allowed to be changed when reissuing the original request.
     *
     */
    public static final int _307 = 307;

    /**
     * Permanent Redirect (Experimental RFC; RFC 7238)
     * The request, and all future requests should be repeated using another URI. 307 and 308 (as proposed) parallel the behaviours of 302 and 301, but do not allow the HTTP
     * method to change.
     */
    public static final int _308 = 308;
    //4xx series

    /**
     * Bad Request
     */
    public static final int _400 = 400;

    /**
     * Unauthorized
     */
    public static final int _401 = 401;

    /**
     * Payment Required
     */
    public static final int _402 = 402;

    /**
     * Forbidden
     */
    public static final int _403 = 403;

    /**
     * Not Found
     */
    public static final int _404 =  404;

    /**
     * Method Not Allowed A request was made of a resource using a request method not supported by that resource;
     */
    public static final int _405 = 405;

    /**
     * Not Acceptable
     * The requested resource is only capable of generating content not acceptable according to the Accept headers sent in the request.
     */
    public static final int _406 = 406;

    /**
     * Proxy Authentication Required
     */
    public static final int _407 = 407;

    /**
     * Request Timeout
     */
    public static final int _408 = 408;

    /**
     * Conflict
     *  Indicates that the request could not be processed because of conflict in the request
     */
    public static final int _409 = 409;

    /**
     * Gone
     * Indicates that the resource requested is no longer available and will not be available again. This should be used when a resource has been intentionally removed
     * and the resource should be purged. Upon receiving a 410 status code, the client should not request the resource again in the future. Clients such as search engines
     * should remove the resource from their indices.
     */
    public static final int _410 = 410;

    /**
     * Length Required
     * The request did not specify the length of its content, which is required by the requested resource.
     */
    public static final int _411 = 411;

    /**
     * Precondition Failed
     */
    public static final int _412 = 412;

    /**
     * Request Entity Too Large
     * The request is larger than the server is willing or able to process.
     */
    public static final int _413 = 413;

    /**
     * Request-URI Too Long
     * The URI provided was too long for the server to process.
     */
    public static final int _414 = 414;

    /**
     * Unsupported Media Type
     * The request entity has a media type which the server or resource does not support.
     */
    public static final int _415 = 415;

    /**
     * Requested Range Not Satisfiable
     * The client has asked for a portion of the file (byte serving), but the server cannot supply that portion.
     */
    public static final int _416 = 416;

    /**
     * Expectation Failed
     * The server cannot meet the requirements of the Expect request-header field.
     */
    public static final int _417 = 417;

    /**
     * I'm a teapot (RFC 2324)
     * This code was defined in 1998 as one of the traditional IETF April Fools' jokes, in RFC 2324, Hyper Text Coffee Pot Control Protocol, and is not expected to be
     * implemented by actual HTTP servers.
     */
    public static final int _418 = 418;

    /**
     * Authentication Timeout
     */
    public static final int _419 = 419;

    /**
     * Method Failure (Spring Framework)Not part of the HTTP standard, but defined by Spring in the HttpStatus class to be used when a method failed.
     *  This status code is deprecated by Spring.
     *                 &amp;
     * Enhance Your Calm (Twitter)
     * Not part of the HTTP standard, but returned by version 1 of the Twitter Search and Trends API when the client is being rate limited.[12] Other services may wish to
     * implement the 429 Too Many Requests response code instead.
     */
    public static final int _420 = 420;

    /**
     * Unprocessable Entity (WebDAV; RFC 4918)
     * The request was well-formed but was unable to be followed due to semantic errors.[3]
     */
    public static final int _422 = 422;

    /**
     * Locked (WebDAV; RFC 4918)
     * The resource that is being accessed is locked.[
     */
    public static final int _423 = 423;

    /**
     * Failed Dependency (WebDAV; RFC 4918)
     * The request failed due to failure of a previous request (e.g., a PROPPATCH).
     */
    public static final int _424 = 424;

    /**
     * Upgrade Required
     * The client should switch to a different protocol such as TLS/1.0.
     */
    public static final int _426 = 426;

    /**
     * Precondition Required (RFC 6585)
     * The origin server requires the request to be conditional. Intended to prevent "the 'lost update' problem, where a client GETs a resource's state, modifies it, and PUTs
     * it back to the server, when meanwhile a third party has modified the state on the server, leading to a conflict.
     */
    public static final int _428 = 428;

    /**
     * Too Many Requests (RFC 6585)
     * The user has sent too many requests in a given amount of time. Intended for use with rate limiting schemes.
     */
    public static final int _429 = 429;

    /**
     * Request Header Fields Too Large (RFC 6585)
     * The server is unwilling to process the request because either an individual header field, or all the header fields collectively, are too large.
     */
    public static final int _431 = 431;

    /**
     * Login Timeout (Microsoft)
     * A Microsoft extension. Indicates that your session has expired.
     */
    public static final int _440 = 440;

    /**
     * No Response (Nginx)
     * Used in Nginx logs to indicate that the server has returned no information to the client and closed the connection (useful as a deterrent for malware).
     */
    public static final int _444 = 444;

    /**
     * Retry With (Microsoft)
     * A Microsoft extension. The request should be retried after performing the appropriate action.
     */
    public static final int _449 = 449;

    /**
     * Blocked by Windows Parental Controls (Microsoft)
     * A Microsoft extension. This error is given when Windows Parental Controls are turned on and are blocking access to the given webpage.
     */
    public static final int _450 = 450;

    /**
     * Unavailable For Legal Reasons (Internet draft)
     * Defined in the internet draft "A New HTTP Status Code for Legally-restricted Resources".[17] Intended to be used when resource access is denied for legal reasons,
     *             &amp;
     * Redirect (Microsoft)
     * Used in Exchange ActiveSync if there either is a more efficient server to use or the server cannot access the users' mailbox.
     *
     */
    public static final int _451 = 451;


    /**
     * Request Header Too Large (Nginx)
     * Nginx internal code similar to 431 but it was introduced earlier in version 0.9.4 (on January 21, 2011).
     */
    public static final int _494 = 494;

    /**
     * Cert Error (Nginx)
     * Nginx internal code used when SSL client certificate error occurred to distinguish it from 4XX in a log and an error page redirection.
     */
    public static final int _495 = 495;

    /**
     * No Cert (Nginx)
     * Nginx internal code used when client didn't provide certificate to distinguish it from 4XX in a log and an error page redirection.
     */
    public static final int _496 = 496;

    /**
     * HTTP to HTTPS (Nginx)
     * Nginx internal code used for the plain HTTP requests that are sent to HTTPS port to distinguish it from 4XX in a log and an error page redirection.
     */
    public static final int _497 = 497;

    /**
     * Token expired/invalid (Esri)
     * Returned by ArcGIS for Server. A code of 498 indicates an expired or otherwise invalid token.
     */
    public static final int _498 = 498;

    /**
     * Client Closed Request (Nginx)
     * Used in Nginx logs to indicate when the connection has been closed by client while the server is still processing its request, making server unable to send a status code back.
     * &amp;
     * Token required (Esri)
     * Returned by ArcGIS for Server. A code of 499 indicates that a token is required (if no token was submitted).
     */
    public static final int _499 = 499;


    //5xx series

    /**
     * Internal Server Error
     */
    public static final int _500 = 500;

    /**
     * Not Implemented
     * The server either does not recognize the request method, or it lacks the ability to fulfil the request. Usually this implies future availability
     */
    public static final int _501 = 501;

    /**
     * Bad Gateway
     * The server was acting as a gateway or proxy and received an invalid response from the upstream server.
     */
    public static final int _502 = 502;

    /**
     * Service Unavailable
     * The server is currently unavailable (because it is overloaded or down for maintenance). Generally, this is a temporary state.
     */
    public static final int _503 = 503;

    /**
     * Gateway Timeout
     * The server was acting as a gateway or proxy and did not receive a timely response from the upstream server.
     */
    public static final int _504 = 504;

    /**
     * HTTP Version Not Supported
     * The server does not support the HTTP protocol version used in the request.
     */
    public static final int _505 = 505;

    /**
     * Variant Also Negotiates (RFC 2295)
     * Transparent content negotiation for the request results in a circular reference.[
     */
    public static final int _506 = 506;

    /**
     * Insufficient Storage (WebDAV; RFC 4918)
     * The server is unable to store the representation needed to complete the request.
     */
    public static final int _507 = 507;

    /**
     * Loop Detected (WebDAV; RFC 5842)
     * The server detected an infinite loop while processing the request (sent in lieu of 208 Already Reported).
     */
    public static final int _508 = 508;

    /**
     * Bandwidth Limit Exceeded (Apache bw/limited extension)
     * This status code is not specified in any RFCs. Its use is unknown.
     */
    public static final int _509 = 509;

    /**
     * Not Extended (RFC 2774)
     * Further extensions to the request are required for the server to fulfil it.
     */
    public static final int _510 = 510;

    /**
     * Network Authentication Required (RFC 6585)
     * The client needs to authenticate to gain network access. Intended for use by intercepting proxies used to control access to the network
     */
    public static final int _511 = 511;

    /**
     * Origin Error (CloudFlare)
     * This status code is not specified in any RFCs, but is used by CloudFlare's reverse proxies to signal an "unknown connection issue between CloudFlare and the origin
     *  web server" to a client in front of the proxy.
     */
    public static final int _520 = 520;

    /**
     * Web server is down (CloudFlare)
     * This status code is not specified in any RFCs, but is used by CloudFlare's reverse proxies to indicate that the origin webserver refused the connection.
     */
    public static final int _521 = 521;

    /**
     * Connection timed out (CloudFlare)
     * This status code is not specified in any RFCs, but is used by CloudFlare's reverse proxies to signal that a server connection timed out.
     */
    public static final int _522 = 522;

    /**
     *  Proxy Declined Request (CloudFlare)
     *  This status code is not specified in any RFCs, but is used by CloudFlare's reverse proxies to signal a resource that has been blocked by the administrator of the website
     *  or proxy itself.
     */
    public static final int _523 = 523;

    /**
     * A timeout occurred (CloudFlare)
     * This status code is not specified in any RFCs, but is used by CloudFlare's reverse proxies to signal a network read timeout behind the proxy to a client in front of the proxy.
     */
    public static final int _524 = 524;

    /**
     * Network read timeout error (Unknown)
     * This status code is not specified in any RFCs, but is used by Microsoft HTTP proxies to signal a network read timeout behind the proxy to a client in front of the proxy.
     */
    public static final int _598 = 598;

    /**
     * Network connect timeout error (Unknown)
     * This status code is not specified in any RFCs, but is used by Microsoft HTTP proxies to signal a network connect timeout behind the proxy to a client in front of the proxy.
     */
    public static final int _599 = 599;
}
