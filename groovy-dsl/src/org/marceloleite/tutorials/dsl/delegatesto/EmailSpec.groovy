package org.marceloleite.tutorials.dsl.delegatesto

class EmailSpec {

    void from(String from) {
        println "From: $from"
    }

    void to(String... to) {
        println "To: $to"
    }

    void subject(String subject) {
        println "Subject: $subject"
    }

    void body(
            @DelegatesTo(BodySpec)
            Closure body) {
        def bodySpec = new BodySpec()
        def code = body.rehydrate(bodySpec, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }
}
