package com.example.test.gitplugin;

import com.example.test.gitplugin.Utils.GitHttpClient;
import com.example.test.gitplugin.testDto.GCCPreviewResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.ConnectionPoolTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NoHttpResponseException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpServiceTest {

    public static void main(String[] args) throws Exception {
//        uploadPreviewHtml();
//        notifyMessage();
//        getConnectorSessionObjectViaAdminApi("ddb79ccc-0cfd-47c7-9159-51801d0f0c75");
//        cloneProgram();
//        updateDynamicContentRestTemplate();
        writeIntoBigDatastore();
//        readBigDatastore();
    }

    public static void uploadPreviewHtml() throws JsonProcessingException {

        String html = "<!DOCTYPE html>\\n<html lang=\\\"en\\\"><head>\\n    <meta charset=\\\"utf-8\\\">\\n    <meta name=\\\"viewport\\\" content=\\\"width=device-width, initial-scale=1, maximum-scale=1\\\">\\n    \\n    \\n    <meta name=\\\"robots\\\" content=\\\"index, nofollow\\\">\\n\\n    <!--  Favicon -->\\n    <link rel=\\\"shortcut icon\\\" sizes=\\\"16x16 24x24 32x32\\\" href=\\\"https://s3-us-west-2.amazonaws.com/ho-websites/marketo/img/favicon.ico\\\">\\n\\n    <!-- For iPad with high-resolution Retina display running iOS ≥ 7: -->\\n    <link rel=\\\"apple-touch-icon-precomposed\\\" sizes=\\\"152x152\\\" href=\\\"https://s3-us-west-2.amazonaws.com/ho-websites/marketo/img/favicon-152.png\\\">\\n    <!-- For iPad with high-resolution Retina display running iOS ≤ 6: -->\\n    <link rel=\\\"apple-touch-icon-precomposed\\\" sizes=\\\"144x144\\\" href=\\\"https://s3-us-west-2.amazonaws.com/ho-websites/marketo/img/favicon-144.png\\\">\\n    <!-- For iPhone with high-resolution Retina display running iOS ≥ 7: -->\\n    <link rel=\\\"apple-touch-icon-precomposed\\\" sizes=\\\"120x120\\\" href=\\\"https://s3-us-west-2.amazonaws.com/ho-websites/marketo/img/favicon-120.png\\\">\\n    <!-- For iPhone with high-resolution Retina display running iOS ≤ 6: -->\\n    <link rel=\\\"apple-touch-icon-precomposed\\\" sizes=\\\"114x114\\\" href=\\\"https://s3-us-west-2.amazonaws.com/ho-websites/marketo/img/favicon-114.png\\\">\\n    <!-- For first- and second-generation iPad: -->\\n    <link rel=\\\"apple-touch-icon-precomposed\\\" sizes=\\\"72x72\\\" href=\\\"https://s3-us-west-2.amazonaws.com/ho-websites/marketo/img/favicon-72.png\\\">\\n    <!-- For non-Retina iPhone, iPod Touch, and Android 2.1+ devices: -->\\n    <link rel=\\\"apple-touch-icon-precomposed\\\" href=\\\"https://s3-us-west-2.amazonaws.com/ho-websites/marketo/img/favicon-57.png\\\">\\n\\n    <!-- BASIC INFO -->\\n    <title>${pageTitle}</title>\\n\\n    <!-- GOOGLE FONTS -->\\n    <link rel=\\\"stylesheet\\\" href=\\\"//fonts.googleapis.com/css?family=Lato:300,400,700,900'\\\">\\n\\n    <!-- https://s3-us-west-2.amazonaws.com/ho-websites/marketo/ -->\\n    <!-- SPECIFIC CSS -->\\n    <link rel=\\\"stylesheet\\\" href=\\\"https://s3-us-west-2.amazonaws.com/ho-websites/marketo/css/framework.css\\\">\\n\\n    <!-- Optimizely -->\\n    <script src=\\\"//cdn.optimizely.com/js/2888241030.js\\\"></script>\\n    \\n    <!-- Google Tag Manager and Data Layer -->\\n    <script>\\n        window.dataLayer = window.dataLayer || [];\\n        window.dataLayer.push({\\n            'event': 'dataLayer-loaded'\\n        });\\n        \\n        (function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start': new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src='https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);\\n        })(window,document,'script','dataLayer','GTM-WR52GC');\\n    </script>\\n    <!-- End Google Tag Manager -->\\n\\n    <style type=\\\"text/css\\\">\\n        .hero {\\n            background-image: url(https://s3-us-west-2.amazonaws.com/ho-websites/marketo/img/hero-bg.jpg);\\n        }\\n\\n        #formTitle {\\n            display:block;\\n        }\\n      .soumyaCSS{\\n        background: \\\"red\\\";\\n      }\\n\\n    </style>\\n    <link rel=\\\"shortcut icon\\\" href=\\\"/favicon.ico\\\" type=\\\"image/x-icon\\\" >\\n<link rel=\\\"icon\\\" href=\\\"/favicon.ico\\\" type=\\\"image/x-icon\\\" >\\n\\n<link rel=\\\"stylesheet\\\" type=\\\"text/css\\\" href=\\\"//fonts.googleapis.com/css?family=Lato\\\">\\n<style>.mktoGen.mktoImg {display:inline-block; line-height:0;}</style>\\n    </head>\\n\\n    <body id=\\\"bodyId\\\">\\n      \\n      <div>TUNE</div>\\n        <!-- Google Tag Manager (noscript)-->\\n        <noscript>\\n            <iframe src=\\\"https://www.googletagmanager.com/ns.html?id=GTM-WR52GC\\\" height=\\\"0\\\" width=\\\"0\\\" style=\\\"display:none;visibility:hidden\\\"></iframe>\\n        </noscript>\\n        <!-- End Google Tag Manager (noscript)-->\\n        \\n        <div id=\\\"document\\\" class=\\\"document\\\">\\n            <!-- HEADER\\n            ================================= -->\\n            <header id=\\\"header\\\" class=\\\"banded banded--nowhitespace\\\">\\n                <div class=\\\"row\\\">\\n                    <div class=\\\"col-100\\\">\\n                        <div class=\\\"branding-logo branding-logo--hasoffers\\\">\\n                            <a name=\\\"HasOffers\\\" href=\\\"https://www.hasoffers.com/\\\" target=\\\"_top\\\" data-link-type=\\\"header\\\">HasOffers</a>\\n                        </div>\\n\\n                        <div class=\\\"branding-phone\\\">\\n                            <p>855.481.5859</p>\\n                        </div>\\n                    </div>\\n                </div>\\n            </header>\\n\\n              <!-- HERO\\n              ================================= -->\\n            <section class=\\\"hero hero--marketo-landing banded--lesswhitespace\\\">\\n                <div class=\\\"row\\\">\\n                    <div class=\\\"col-80\\\">\\n                      \\n                     \\n                        <div class=\\\"col-55\\\"><!--\\\"col-55 mktoText-->\\n                          \\t<div id=\\\"heading\\\" class=\\\"mktoText\\\">\\n                              <h1>One measurement solution for Mobile and desktop</h1>\\n                         </div>\\n                            \\n                        </div>\\n   \\n                     \\n                        <div class=\\\"col-45\\\">\\n                            <div class=\\\"marketo-form\\\">\\n                              \\n                                <div id=\\\"formTitle\\\" class=\\\"mktoText\\\">\\n                                    <h4>Get the ebook today!</h4>\\n                                </div>\\n\\n                                <div class=\\\"mktoForm\\\" id=\\\"heroForm\\\"><div id='lpeCDiv_28675' class='lpeCElement LandingPageAndFormPratik_Form1'><span class='lpContentsItem formSpan'>\\n<script src=\\\"/js/forms2/js/forms2.min.js\\\"></script>\\n<form class=\\\"mktoForm\\\" id=\\\"mktoForm_1971\\\">\\n</form>\\n<script>\\n  (function (){\\n    var formDescriptor = {\\\"Id\\\":1971,\\\"Vid\\\":1971,\\\"Status\\\":\\\"approved\\\",\\\"Name\\\":\\\"LandingPageAndForm.Pratik_Form1\\\",\\\"Description\\\":\\\"\\\",\\\"Layout\\\":\\\"left\\\",\\\"GutterWidth\\\":10,\\\"OffsetWidth\\\":10,\\\"HasTwoButtons\\\":true,\\\"SubmitLabel\\\":\\\"Submit\\\",\\\"ResetLabel\\\":\\\"Clear\\\",\\\"ButtonLocation\\\":\\\"120\\\",\\\"LabelWidth\\\":100,\\\"FieldWidth\\\":150,\\\"ToolTipType\\\":\\\"none\\\",\\\"FontFamily\\\":\\\"Helvetica, Arial, sans-serif\\\",\\\"FontSize\\\":\\\"13px\\\",\\\"FontColor\\\":\\\"#333\\\",\\\"FontUrl\\\":null,\\\"LineMargin\\\":10,\\\"ProcessorVersion\\\":2,\\\"CreatedByUserid\\\":3337,\\\"ProcessOptions\\\":{\\\"language\\\":\\\"English\\\",\\\"locale\\\":\\\"en_US\\\",\\\"profiling\\\":{\\\"isEnabled\\\":false,\\\"numberOfProfilingFields\\\":3,\\\"alwaysShowFields\\\":[]},\\\"socialSignOn\\\":{\\\"isEnabled\\\":false,\\\"enabledNetworks\\\":[],\\\"cfId\\\":null,\\\"codeSnippet\\\":\\\"\\\"}},\\\"EnableDeferredMode\\\":0,\\\"EnableCaptcha\\\":0,\\\"EnableGlobalFormValidationRule\\\":1,\\\"ButtonType\\\":null,\\\"ButtonImageUrl\\\":null,\\\"ButtonText\\\":null,\\\"ButtonSubmissionText\\\":\\\"Please Wait\\\",\\\"ButtonStyle\\\":{\\\"id\\\":11,\\\"className\\\":\\\"mktoSimple\\\",\\\"css\\\":\\\".mktoForm .mktoButtonWrap.mktoSimple .mktoButton {\\\\ncolor:#fff;\\\\nborder:1px solid #75ae4c;\\\\npadding:0.4em 1em;\\\\nfont-size:1em;\\\\nbackground-color:#99c47c;\\\\nbackground-image: -webkit-gradient(linear, left top, left bottom, from(#99c47c), to(#75ae4c));\\\\nbackground-image: -webkit-linear-gradient(top, #99c47c, #75ae4c);\\\\nbackground-image: -moz-linear-gradient(top, #99c47c, #75ae4c);\\\\nbackground-image: linear-gradient(to bottom, #99c47c, #75ae4c);\\\\n}\\\\n.mktoForm .mktoButtonWrap.mktoSimple .mktoButton:hover {\\\\nborder:1px solid #447f19;\\\\n}\\\\n.mktoForm .mktoButtonWrap.mktoSimple .mktoButton:focus {\\\\noutline:none;\\\\nborder:1px solid #447f19;\\\\n}\\\\n.mktoForm .mktoButtonWrap.mktoSimple .mktoButton:active{\\\\nbackground-color:#75ae4c;\\\\nbackground-image: -webkit-gradient(linear, left top, left bottom, from(#75ae4c), to(#99c47c));\\\\nbackground-image: -webkit-linear-gradient(top, #75ae4c, #99c47c);\\\\nbackground-image: -moz-linear-gradient(top, #75ae4c, #99c47c);\\\\nbackground-image: linear-gradient(to bottom, #75ae4c, #99c47c);\\\\n}\\\\n\\\",\\\"buttonColor\\\":null},\\\"ThemeStyle\\\":{\\\"id\\\":2,\\\"displayOrder\\\":1,\\\"name\\\":\\\"Simple\\\",\\\"backgroundColor\\\":\\\"#FFF\\\",\\\"layout\\\":\\\"left\\\",\\\"fontFamily\\\":\\\"Helvetica, Arial, sans-serif\\\",\\\"fontSize\\\":\\\"13px\\\",\\\"fontColor\\\":\\\"#333\\\",\\\"offsetWidth\\\":10,\\\"gutterWidth\\\":10,\\\"labelWidth\\\":100,\\\"fieldWidth\\\":150,\\\"lineMargin\\\":10,\\\"useBackgroundColorOnPreview\\\":false,\\\"css\\\":\\\".mktoForm fieldset.mkt3-formEditorFieldContainer{border: solid 1px gray;}.mktoForm fieldset.mkt3-formEditorFieldContainer legend{padding:0 1em;}\\\",\\\"href\\\":\\\"css\\/forms2-theme-simple.css\\\",\\\"buttonStyleId\\\":11},\\\"ThemeStyleOverride\\\":null,\\\"rows\\\":[[{\\\"Id\\\":4729,\\\"Name\\\":\\\"FirstName\\\",\\\"IsRequired\\\":true,\\\"Datatype\\\":\\\"string\\\",\\\"Maxlength\\\":255,\\\"InputLabel\\\":\\\"First Name:\\\",\\\"InputInitialValue\\\":\\\"\\\",\\\"InputSourceChannel\\\":\\\"constant\\\",\\\"ValidationMessage\\\":\\\"Fill First Name\\\"}],[{\\\"Id\\\":4730,\\\"Name\\\":\\\"LastName\\\",\\\"IsRequired\\\":true,\\\"Datatype\\\":\\\"string\\\",\\\"Maxlength\\\":255,\\\"InputLabel\\\":\\\"Last Name:\\\",\\\"InputInitialValue\\\":\\\"\\\",\\\"InputSourceChannel\\\":\\\"constant\\\",\\\"ValidationMessage\\\":\\\"Fill Last Name\\\"}],[{\\\"Id\\\":4731,\\\"Name\\\":\\\"Email\\\",\\\"IsRequired\\\":true,\\\"Datatype\\\":\\\"email\\\",\\\"Maxlength\\\":255,\\\"InputLabel\\\":\\\"Email Address:\\\",\\\"InputInitialValue\\\":\\\"\\\",\\\"InputSourceChannel\\\":\\\"constant\\\",\\\"ValidationMessage\\\":\\\"Email Is Compulsory\\\"}]],\\\"fieldsetRows\\\":[],\\\"action\\\":\\\"\\/index.php\\/leadCapture\\/save3\\\",\\\"munchkinId\\\":\\\"987-ZFB-679\\\",\\\"invalidInputMsg\\\":\\\"Invalid Input\\\",\\\"formSubmitFailedMsg\\\":\\\"Submission failed, please try again later.\\\",\\\"isPreview\\\":null};\\n    MktoForms2.setOptions({baseUrl:\\\"/js/forms2/\\\"});\\n    var isDev = false;\\n    if(isDev && window.console && window.JSON){\\n      console.log(JSON.stringify(formDescriptor, null, \\\"  \\\"));\\n    }\\n    formDescriptor.lpId = 6728;\\n    var form = MktoForms2.newForm(formDescriptor, function (form){\\n      var lpFields = {\\\"lpId\\\":6728,\\\"subId\\\":106,\\\"munchkinId\\\":\\\"987-ZFB-679\\\",\\\"lpurl\\\":\\\"http:\\/\\/987-ZFB-679.mktoweb.com\\/lp\\/987-ZFB-679\\/LandingPageAndForm_08-LandingPageWF01.html?cr={creative}&kw={keyword}\\\"};\\n      var pageFields = MktoForms2.getPageFields();\\n      form.addHiddenFields(lpFields);\\n      form.addHiddenFields(pageFields);\\n      if(window.mktoPreFillFields){\\n        form.setValuesCoerced(mktoPreFillFields);\\n      }\\n      if(!form.EnableDeferredMode){\\n        form.render();\\n      }\\n    });     \\n  })()\\n</script>\\n\\n<noscript>\\n  <form class='mktoForm mktoNoJS' action='/index.php/leadCapture/save3' method='post'>\\n        <div class='mktoFormRow'>\\n          <div class='mktoFormCol'>\\n            <label class=\\\"mktoLabel\\\" for='FirstName'>\\n              First Name:            </label>\\n            <input type=\\\"text\\\" class='mktoField mktoTextField' name='FirstName' id='FirstName'>\\n          </div>\\n        </div>\\n        <div class='mktoFormRow'>\\n          <div class='mktoFormCol'>\\n            <label class=\\\"mktoLabel\\\" for='LastName'>\\n              Last Name:            </label>\\n            <input type=\\\"text\\\" class='mktoField mktoTextField' name='LastName' id='LastName'>\\n          </div>\\n        </div>\\n        <div class='mktoFormRow'>\\n          <div class='mktoFormCol'>\\n            <label class=\\\"mktoLabel\\\" for='Email'>\\n              Email Address:            </label>\\n            <input type=\\\"text\\\" class='mktoField mktoTextField' name='Email' id='Email'>\\n          </div>\\n        </div>\\n    <span style=\\\"display:none;\\\"><input type=\\\"text\\\" name=\\\"_marketo_comments\\\" value=\\\"\\\"></span>\\n    <span class='mktoButtonWrap'><button type='submit' class='mktoButton'>Submit</button></span>\\n    <input type=\\\"hidden\\\" name=\\\"lpId\\\" value=\\\"6728\\\" />\\n    <input type=\\\"hidden\\\" name=\\\"subId\\\" value=\\\"106\\\" />\\n    <input type=\\\"hidden\\\" name=\\\"lpurl\\\" value=\\\"http://987-ZFB-679.mktoweb.com/lp/987-ZFB-679/LandingPageAndForm_08-LandingPageWF01.html?cr={creative}&amp;kw={keyword}\\\" />\\n    <input type=\\\"hidden\\\" name=\\\"formid\\\" value=\\\"1971\\\" />\\n    <input type=\\\"hidden\\\" name=\\\"formVid\\\" value=\\\"1971\\\" />\\n    <input type=\\\"hidden\\\" name=\\\"ret\\\" value=\\\"\\\" />\\n    <input type=\\\"hidden\\\" name=\\\"munchkinId\\\" value=\\\"987-ZFB-679\\\"/>\\n    <input type=\\\"hidden\\\" name=\\\"kw\\\" value=\\\"\\\"/>\\n    <input type=\\\"hidden\\\" name=\\\"cr\\\" value=\\\"\\\"/>\\n    <input type=\\\"hidden\\\" name=\\\"searchstr\\\" value=\\\"\\\"/>\\n    <input type=\\\"hidden\\\" name=\\\"_mkt_disp\\\" value=\\\"return\\\"/>\\n    <input type=\\\"hidden\\\" name=\\\"_mkt_trk\\\" value=\\\"\\\"/>\\n  </form>\\n</noscript>\\n\\n</span></div>\\n</div> \\n\\n                            </div>\\n                        </div>  \\n                    </div>\\n                </div>\\n            </section>\\n\\n            <section class=\\\"banded\\\">\\n                <div class=\\\"row\\\">\\n                    <div class=\\\"col-80\\\">\\n\\n                        <div class=\\\"col-33\\\">\\n                            <div class=\\\"mktoImg mktoGen\\\" id=\\\"mkto_gen_docCover\\\">\\n                                <img src=\\\"https://s3-us-west-2.amazonaws.com/ho-websites/marketo/img/ebook-cover.jpg\\\" alt=\\\"Cover\\\" class=\\\"doc-cover-image\\\" id=\\\"docCover\\\">\\n                            </div>\\n                        </div>\\n\\n                        <div class=\\\"col-66\\\">\\n                          <div class=\\\"soumyaCSS\\\">\\n                            <div id=\\\"contentTitle\\\" class=\\\"mktoText heading-padding\\\"><h4>The Complete Guide to Enterprise App Marketing</h4>\\n<div>Token INherited:&nbsp;This is the inherited token test, lets see</div>\\n<div>token:&nbsp;&nbsp;THis is a landing form</div></div>\\n \\t\\t\\t\\t\\t\\t\\t</div>\\n                            <div id=\\\"contentText\\\" class=\\\"mktoText\\\">\\n                                <p>This 100+ page eBook includes comprehensive explanations and best practices for the following topics:</p>\\n\\n                                <ul>\\n                                    <li>Acquisition: Paid and organic user acquisition</li>\\n                                    <li>Activation: On-boarding user experience</li>\\n                                    <li>Retention: Keeping users coming back</li>\\n                                    <li>Referral: Improving customer happiness</li>\\n                                    <li>Revenue: Driving revenue through your app</li>\\n                                    <li>Download the guide today to get started!</li>\\n                                </ul>\\n                            </div>\\n                        </div>\\n                    </div>\\n                </div>\\n            </section>\\n\\n            <!-- FOOTER\\n            ================================= -->\\n            <section id=\\\"footer\\\" class=\\\"footer\\\">\\n                <div class=\\\"row\\\">\\n                    <div class=\\\"col-100\\\">\\n                        <div class=\\\"footer__col\\\">\\n                            <ul>\\n                                <li><a href=\\\"//www.tune.com/resources/\\\" data-link-type=\\\"footer\\\">Resources</a></li>\\n                                <li><a title=\\\"TUNE Blog\\\" href=\\\"//www.tune.com/blog/\\\" data-link-type=\\\"footer\\\">Blog</a></li>\\n                                <li><a href=\\\"//www.hasoffers.com/resources/\\\" data-link-type=\\\"footer\\\">Resource Library</a></li>\\n                                <li><a href=\\\"//www.tune.com/resources/webinars-and-events/\\\" data-link-type=\\\"footer\\\">Webinars and Events</a></li>\\n                            </ul>\\n                        </div>\\n\\n                        <div class=\\\"footer__col\\\">\\n                            <ul>\\n                                <li><a href=\\\"//www.tune.com/who-we-help/\\\" data-link-type=\\\"footer\\\">Who We Help</a></li>\\n                                <li><a href=\\\"//www.tune.com/who-we-help/#marketers\\\" data-link-type=\\\"footer\\\">Marketers and Agencies</a></li>\\n                                <li><a href=\\\"//www.tune.com/solutions/tune-connect/\\\" data-link-type=\\\"footer\\\">Tech and Ad Partners</a></li>\\n                            </ul>\\n                        </div>\\n\\n                        <div class=\\\"footer__col\\\">\\n                            <ul>\\n                                <li><a href=\\\"//www.tune.com/who-we-are/\\\" data-link-type=\\\"footer\\\">Who We Are</a></li>\\n                                <li><a href=\\\"//www.tune.com/who-we-are/contact-us/\\\" data-link-type=\\\"footer\\\">Contact Us</a></li>\\n                                <li><a href=\\\"//www.tune.com/who-we-are/our-company/\\\" data-link-type=\\\"footer\\\">Our Company</a></li>\\n                            </ul>\\n                        </div>\\n\\n                        <div class=\\\"footer__col\\\">\\n                            <ul>\\n                                <li>Connect with us</li>\\n                                <li class=\\\"footer__social\\\">\\n                                    <a href=\\\"https://www.facebook.com/TUNE/\\\" target=\\\"_blank\\\"> <img src=\\\"https://s3-us-west-2.amazonaws.com/ho-websites/marketo/img/social-icon-facebook.svg\\\" alt=\\\"Facebook\\\"></a>\\n                                    <a href=\\\"https://twitter.com/tune\\\" target=\\\"_blank\\\"><img src=\\\"https://s3-us-west-2.amazonaws.com/ho-websites/marketo/img/social-icon-twitter.svg\\\" alt=\\\"Twitter\\\"></a>\\n                                    <a href=\\\"https://www.linkedin.com/company/tune\\\" target=\\\"_blank\\\"><img src=\\\"https://s3-us-west-2.amazonaws.com/ho-websites/marketo/img/social-icon-linkedin.svg\\\" alt=\\\"LinkedIn\\\"></a>\\n                                </li>\\n                            </ul>\\n                        </div>\\n\\n                        <div class=\\\"footer__col\\\">\\n                            <img src=\\\"https://s3-us-west-2.amazonaws.com/ho-websites/marketo/img/tune-logo.png\\\" alt=\\\"TUNE Marketing Console\\\" width=\\\"100\\\">\\n                            <p>© 2016 <span class=\\\"text-uppercase\\\">Tune</span>, Inc.</p>\\n                            <p><a href=\\\"https://www.tune.com/resources/data-and-privacy/privacy-policies/\\\" title=\\\"Privacy Policy\\\" data-link-type=\\\"footer\\\">Privacy Policy</a></p>\\n                            <p><a href=\\\"https://www.tune.com/resources/data-and-privacy/terms-of-use/\\\" title=\\\"Terms of Use\\\" data-link-type=\\\"footer\\\">Terms of Use</a></p>\\n                        </div>\\n                    </div>\\n                </div>\\n            </section>\\n\\n        </div>\\n    <script type=\\\"text/javascript\\\" src=\\\"//munchkin.marketo.net//munchkin.js\\\"></script><script>Munchkin.init('987-ZFB-679', {customName: 'LandingPageAndForm_08-LandingPageWF01', PURL_VISIT_TOKEN, wsInfo: 'j1RR'});</script>\\n<div id=\\\"mktoClickBlockingDiv\\\"></div>\\n    </body>\\n\\n</html>\\n";
        String previewHtml = StringEscapeUtils.unescapeJava(html);

        RestTemplate restTemplate = new RestTemplate();
        String previewServerUrl = "https://connect-dev.translations.com/rest-api/v3/context/upload/html";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

        String fileName = "preview_test_007.html";
        map.add("html_file_name", fileName);
        ByteArrayResource contentsAsResource = new ByteArrayResource(previewHtml.getBytes(StandardCharsets.UTF_8)) {
            @Override
            public String getFilename() {
                return fileName;
            }
        };
        map.add("preview_file", contentsAsResource);
        map.add("connector_key", "1b39b08ae1c70c8e09ce01ce37bef083");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.add("api-key", "tD6wK3YUDhSbWkGn");

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(previewServerUrl, request, String.class);

//        Map<String, Object> mapRes = new ObjectMapper().readValue(response.getBody(), new TypeReference<HashMap<String,Object>>(){});
        GCCPreviewResponse previewresponse = new ObjectMapper().readValue(response.getBody(), GCCPreviewResponse.class);
        System.out.println(previewresponse.getResponseData().getContextUrl());
    }

    public static Optional<GitConnectorConfig> getConnectorSessionObjectViaAdminApi(String sessionId) throws Exception {

        GetMethod get = null;
        Map<String, Object> res = null;
        GitConnectorConfig connectorConfig = null;
        ObjectMapper mapper = null;
        for (int i = 1; i <= 5; i++) {
            try {
                HttpClient httpClient = GitHttpClient.getHttpInstance();
                get = new GetMethod("https://connect-qa.translations.com/admin-api/connector-session/" + sessionId);
                get.setRequestHeader("api-key", "password1");
                httpClient.executeMethod(get);
                mapper = new ObjectMapper();
                res = mapper.readValue(get.getResponseBodyAsString(), Map.class);

                if (res.get("status") != null) {
                    int status = (Integer) res.get("status");
                    String message = (String) res.get("message");
                    System.out.println("Status: " + status + " Message: " + message);

                    if (status == 404 && StringUtils.isNotBlank(message) && message.equals("Requested connector-session-key does not exist in cache")) {
                        throw new Exception("Invalid");
                    }
                }

                connectorConfig = mapper.readValue(mapper.writeValueAsString(res.get("response_data")), GitConnectorConfig.class);

                return Optional.ofNullable(connectorConfig);

            } finally {
                if (get != null) {
                    get.releaseConnection();
                }
            }
        }
        return Optional.empty();
    }

    @SuppressWarnings("unchecked")
    public static void notifyMessage() throws Exception {

        PostMethod postMethod = null;

        for (int i = 1; i <= 5; i++) {
            try {
                HttpClient httpClient = GitHttpClient.getHttpInstance();

                String connectorKey = "f951b84241f06027a5e43f662296ed77";

                NotifyRequest notifyRequest = new NotifyRequest(0, 0, 0, "test", "test", connectorKey);

                String jsonString = new ObjectMapper().writeValueAsString(notifyRequest);
                StringRequestEntity requestEntity = new StringRequestEntity(jsonString, "application/json", "UTF-8");
//                postMethod = new PostMethod(config.getGccUrl() + DataStoreConstants.DATASTORE);
                postMethod = new PostMethod(validateUrl("https://connect-dev.translations.com/rest-api/v3") + "util/notify");
//                postMethod.setRequestHeader(DataStoreConstants.API_KEY, config.getConnectorApiKey());
                postMethod.setRequestHeader("api-key", "tD6wK3YUDhSbWkGn");
                postMethod.setRequestEntity(requestEntity);

                httpClient.executeMethod(postMethod);
                Map<Object, Object> result = new ObjectMapper().readValue(postMethod.getResponseBodyAsString(),
                        Map.class);

                Integer status = (Integer) result.get("status");
                if (status == 200) {
                    break;
                } else {
                    throw new Exception("Failed to Notify" + postMethod.getResponseBodyAsString());
                }
            } catch (Exception e) {
                // NoHttpResponseException =>when under heavy load
                // ConnectTimeoutException => unable to establish a connection
                // ConnectionPoolTimeoutException => can only occur when using the multithreaded
                // connection manager
                // fails to obtain a free connection from the connection pool
                if (e instanceof NoHttpResponseException || e instanceof ConnectTimeoutException
                        || e instanceof ConnectionPoolTimeoutException) {
                    Thread.sleep(500 * i);
                    continue;
                } else {
                    throw e;
                }
            } finally {
                if (postMethod != null) {
                    postMethod.releaseConnection();
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    public static void cloneProgram() throws Exception {

        PostMethod postMethod = null;

        for (int i = 1; i <= 5; i++) {
            try {
                HttpClient httpClient = GitHttpClient.getHttpInstance();

                Folder folder = new Folder(1675, "Folder");
                CloneProgramRequest request = new CloneProgramRequest("GCTS-12382+clone_API", folder);

                String jsonString = new ObjectMapper().writeValueAsString(request);
                StringRequestEntity requestEntity = new StringRequestEntity(jsonString, "application/json", "UTF-8");
//                postMethod = new PostMethod(config.getGccUrl() + DataStoreConstants.DATASTORE);
                postMethod = new PostMethod("https://987-ZFB-679.mktorest.com/rest/asset/v1/program/1679/clone.json?access_token=47ad135b-dd44-4395-a146-0fc1e08dabec:ab");
//                postMethod.setRequestHeader(DataStoreConstants.API_KEY, config.getConnectorApiKey());
//                postMethod.setRequestHeader("api-key", "tD6wK3YUDhSbWkGn");
                postMethod.setRequestEntity(requestEntity);

                httpClient.executeMethod(postMethod);
                Map<Object, Object> result = new ObjectMapper().readValue(postMethod.getResponseBodyAsString(),
                        Map.class);

                Integer status = (Integer) result.get("status");
                if (status == 200) {
                    break;
                } else {
                    throw new Exception("Failed to Notify" + postMethod.getResponseBodyAsString());
                }
            } catch (Exception e) {
                // NoHttpResponseException =>when under heavy load
                // ConnectTimeoutException => unable to establish a connection
                // ConnectionPoolTimeoutException => can only occur when using the multithreaded
                // connection manager
                // fails to obtain a free connection from the connection pool
                if (e instanceof NoHttpResponseException || e instanceof ConnectTimeoutException
                        || e instanceof ConnectionPoolTimeoutException) {
                    Thread.sleep(500 * i);
                    continue;
                } else {
                    throw e;
                }
            } finally {
                if (postMethod != null) {
                    postMethod.releaseConnection();
                }
            }
        }

    }

    public static void writeIntoBigDatastore() throws Exception {

        PostMethod postMethod = null;

        for (int i = 1; i <= 5; i++) {
            try {
                HttpClient httpClient = GitHttpClient.getHttpInstance();

                Map<Object, Object> wrapper = new HashMap<>();

                Map<String, String> branchCommits = new HashMap<>();
                branchCommits.put("gyFour", "5865219d32fc68c9c50bbc38bc6d0a8b774e656a");
                branchCommits.put("manifest-multi", "b883d71c9787f1821edc5e1f8fe816c1018a88e4");

                Map<Object, Object> map = new HashMap<>();
                for (Map.Entry<String, String> e : branchCommits.entrySet()) {
                    map.put(e.getKey(), e.getValue());
                }

//                String jsonMap = "{\n" +
//                        "              \"gyFour\": \"5865219d32fc68c9c50bbc38bc6d0a8b774e656a\",\n" +
//                        "              \"manifest-multi\": \"b883d71c9787f1821edc5e1f8fe816c1018a88e4\"\n" +
//                        "            }";

//                Map<Object, Object> map = new ObjectMapper().readValue(jsonMap, new TypeReference<>() {
//                });

//                List<Map<String, Object>> data = new ArrayList<>();
                List<DatastoreEntry> data = new ArrayList<>();
                DatastoreEntry entry = new DatastoreEntry("pollId-1", map);
//                Map<String, Object> entry = new HashMap<>();
//                entry.put("key", "pollId-1");
//                entry.put("value", map);
                data.add(entry);

                wrapper.put("connector_key", "f951b84241f06027a5e43f662296ed77");
                wrapper.put("data", data);

                String jsonString = new ObjectMapper().writeValueAsString(wrapper);

                StringRequestEntity requestEntity = new StringRequestEntity(jsonString, "application/json", "UTF-8");
                postMethod = new PostMethod(validateUrl("https://connect-dev.translations.com/rest-api/v3/") + "big-datastore");
                postMethod.setRequestHeader("api-key", "tD6wK3YUDhSbWkGn");
                postMethod.setRequestEntity(requestEntity);

                httpClient.executeMethod(postMethod);
                Map<Object, Object> result = new ObjectMapper().readValue(postMethod.getResponseBodyAsString(),
                        Map.class);

                Integer status = (Integer) result.get("status");
                if (status == 200) {
                    break;
                } else {
                    throw new Exception("DataStore update failed" + postMethod.getResponseBodyAsString());
                }
            } catch (Exception e) {
                // NoHttpResponseException =>when under heavy load
                // ConnectTimeoutException => unable to establish a connection
                // ConnectionPoolTimeoutException => can only occur when using the multithreaded
                // connection manager
                // fails to obtain a free connection from the connection pool
                if (e instanceof NoHttpResponseException || e instanceof ConnectTimeoutException
                        || e instanceof ConnectionPoolTimeoutException) {
                    Thread.sleep(500 * i);
                    continue;
                } else {
                    throw e;
                }
            } finally {
                if (postMethod != null) {
                    postMethod.releaseConnection();
                }
            }
        }

    }

    public static void readBigDatastore() throws Exception {
//        Logger.trace("In readMapFromDatastore connectionInfo : " + connectionInfo.toString());

        GetMethod get = null;
        String datastoreUrl = null;
        Map<Object, Object> fileChecks = null;

        for (int i = 1; i <= 5; i++) {
            try {
                HttpClient httpClient = GitHttpClient.getHttpInstance();
                datastoreUrl = validateUrl("https://connect-dev.translations.com/rest-api/v3/") + "big-datastore";
//                Logger.info("Datastore url: " + datastoreUrl);
//                String url = datastoreUrl + "?connector_key=" + "f951b84241f06027a5e43f662296ed77&key=branchCommits";
                String url = datastoreUrl + "?connector_key=" + "f951b84241f06027a5e43f662296ed77&key=pollId-1";
                get = new GetMethod(url);
                get.setRequestHeader("api-key", "tD6wK3YUDhSbWkGn");

                httpClient.executeMethod(get);
//                Logger.trace("Details from data store [" + get.getResponseBodyAsString() + "]");

                Map<Object, Object> map = new ObjectMapper().readValue(get.getResponseBodyAsString(), Map.class);

                Integer status = (Integer) map.get("status");
                String message = (String) map.get("message");
                List<Map<Object, Object>> responseData = (List<Map<Object, Object>>) map.get("response_data");
                String key = (String) responseData.get(0).get("key");
                Map<Object, Object> data = (Map<Object, Object>) responseData.get(0).get("value");

                Map<String, String> dataStringMap = new HashMap<>();
                Map<Object, Object> mapToObj = new HashMap<>();
                for (Map.Entry<Object, Object> entry : data.entrySet()) {
                    dataStringMap.put(entry.getKey().toString(), entry.getValue().toString());
                }

//                Logger.trace("datastore [" + data.get(GCCConstants.DATASTORE) + "]");
                System.out.println(dataStringMap);
                System.out.println(mapToObj);
//                fileChecks = new ObjectMapper().readValue(checkSum, Map.class);

                if (status == 200) {
//                    return fileChecks;
                } else {
//                    Logger.error("Failed to get datastore: " + get.getResponseBodyAsString());
                }

            } catch (Exception e) {
                // NoHttpResponseException =>when under heavy load
                // ConnectTimeoutException => unable to establish a connection
                // ConnectionPoolTimeoutException => can only occur when using the multithreaded
                // connection manager
                // fails to obtain a free connection from the connection pool
                if (e instanceof NoHttpResponseException || e instanceof ConnectTimeoutException
                        || e instanceof ConnectionPoolTimeoutException) {

                    Thread.sleep(500 * i);
                    continue;
                } else {
                    throw e;
                }
            } finally {
                if (get != null) {
                    get.releaseConnection();
                }
            }
        }
//        return fileChecks;
    }

    public static void updateDynamicContentRestTemplate() throws Exception {

        PostMethod postMethod = null;

        for (int i = 1; i <= 5; i++) {
            try {
                // Create RestTemplate instance
                RestTemplate restTemplate = new RestTemplate();

                // Set the request headers
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

                // Set the request parameters
                MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
                map.add("type", "Image");
                map.add("segment", "fr-ca");
                map.add("altText", "Ic%C3%B4ne+de+l%27IA+g%C3%A9n%C3%A9rative+pour+chaque+jeune+entreprise");
                map.add("externalUrl", StringUtils.replace("https://pages.awscloud.com/rs/112-TZM-766/images/Responsible AI_3.png?version=0", " ", "%20"));


                // Create the HttpEntity with headers and parameters
                HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

                // Specify the endpoint URL
                String url = "https://112-TZM-766.mktorest.com/rest/asset/v1/email/922802/dynamicContent/RVMtTGVmdC1JY29uMTBlYzNhOTAzLTRmZGMtNDNiNy04ZTg5LTZmNjA5NzIyNjJhYg==.json?access_token=fb5b7379-ca03-4c07-b00a-cd323d9f8b1b:sj";

                // Make the POST request
                ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

                // Process the response as needed
                String responseBody = response.getBody();
                System.out.println("Response: " + responseBody);


//                Integer status = (Integer) result.get("status");
//                if (status == 200) {
//                    break;
//                } else {
//                    throw new Exception("Failed to Notify" + postMethod.getResponseBodyAsString());
//                }
            } catch (Exception e) {
                // NoHttpResponseException =>when under heavy load
                // ConnectTimeoutException => unable to establish a connection
                // ConnectionPoolTimeoutException => can only occur when using the multithreaded
                // connection manager
                // fails to obtain a free connection from the connection pool
                if (e instanceof NoHttpResponseException || e instanceof ConnectTimeoutException
                        || e instanceof ConnectionPoolTimeoutException) {
                    Thread.sleep(500 * i);
                    continue;
                } else {
                    throw e;
                }
            } finally {
                if (postMethod != null) {
                    postMethod.releaseConnection();
                }
            }
        }

    }

    private static String validateUrl(String url) {
        if (StringUtils.endsWith(url, "/")) {
            return url;
        }
        return url + "/";
    }
}


class CloneProgramRequest {
    @JsonProperty("name")
    String name;
    @JsonProperty("folder")
    Folder folder;

    public CloneProgramRequest(String name, Folder folder) {
        this.name = name;
        this.folder = folder;
    }
}

class Folder {
    @JsonProperty("id")
    Integer id;
    @JsonProperty("type")
    String type;

    public Folder(Integer id, String type) {
        this.id = id;
        this.type = type;
    }
}

class DatastoreEntry {
    private String key;
    private Object value;

    public DatastoreEntry(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

class NotifyRequest {
    @JsonProperty("job_id")
    private long jobId;
    @JsonProperty("task_id")
    private long taskId;
    @JsonProperty("submission_id")
    private long submissionId;
    @JsonProperty("message")
    private String message;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("connector_key")
    private String connectorKey;

    public NotifyRequest(long jobId, long taskId, long submissionId, String message, String subject, String connectorKey) {
        this.jobId = jobId;
        this.taskId = taskId;
        this.submissionId = submissionId;
        this.message = message;
        this.subject = subject;
        this.connectorKey = connectorKey;
    }
}
