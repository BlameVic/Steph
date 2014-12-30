# -*- coding: utf-8 -*-
class Steph::Execute
    include Cinch::Plugin
    include Steph::Utils

    command /admin\/execute (.*)/
    # @param [Cinch::Message] m
    def execute(m, match)
        return "Permission Denied" unless m.channel.ops.include? m.user
        eval(match)
    end
end