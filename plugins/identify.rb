# -*- coding: utf-8 -*-
class Steph::Identify
    include Cinch::Plugin

    listen_to :connect, method: :identify
    def identify(m)
        case config[:type]
        when :nickserv
            debug "Identifying with NickServ"
            identify_nickserv
        else
            debug "Not going to identify with unknown type #{config[:type].inspect}"
        end
    end

    def identify_nickserv
        if config[:username]
            User("nickserv").send("identify %s %s" % [config[:username], config[:password]])
        else
            User("nickserv").send("identify %s" % [config[:password]])
        end
    end
    private :identify_nickserv

    match(/^You are successfully identified as/, use_prefix: false, use_suffix: false, react_on: :private, method: :identified_nickserv)
    match(/^You are now identified for/,         use_prefix: false, use_suffix: false, react_on: :private, method: :identified_nickserv)
    def identified_nickserv(m)
        if m.user == User("nickserv") && config[:type] == :nickserv
            debug "Identified with NickServ"
            @bot.handlers.dispatch :identified, m
        end
    end
end

